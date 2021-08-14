package com.kovecmedia.redseat.job;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kovecmedia.redseat.doa.JobHistoryRepository;
import com.kovecmedia.redseat.doa.MessageQueueRepository;
import com.kovecmedia.redseat.doa.PackageRepository;
import com.kovecmedia.redseat.doa.ScheduleRepository;
import com.kovecmedia.redseat.doa.UserRepository;
import com.kovecmedia.redseat.entity.JobHistory;
import com.kovecmedia.redseat.entity.MessageQueue;
import com.kovecmedia.redseat.entity.Package;
import com.kovecmedia.redseat.entity.ScheduledJob;
import com.kovecmedia.redseat.entity.User;
import com.kovecmedia.redseat.model.JobStatus;
import com.kovecmedia.redseat.model.MessageStatus;
import com.kovecmedia.redseat.model.PackagesStatus;
import com.kovecmedia.redseat.service.EmailService;

import ch.qos.logback.classic.Level;

@Component
public class EmailSchedule {
	private Logger logger = Logger.getLogger(this.getClass());

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private EmailService emailService;

	@Autowired
	private MessageQueueRepository messageQueueRepository;

	@Autowired
	private JobHistoryRepository jobHistoryRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PackageRepository packageepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	long POINTSLIMIT =10;
	
	

	@Scheduled(fixedDelay = 300000) // 5 minutes
	public void runSchedule() throws MessagingException, IOException {

		long millis = System.currentTimeMillis();
		JobHistory history = new JobHistory();
		java.sql.Date date = new java.sql.Date(millis);
		java.sql.Time time = new java.sql.Time(millis);
		history.setRundate(date);
		history.setRuntime(time);
		
		populatedQueueRepository();
		history.setStatus(JobStatus.Rnuning);
         
		try {

			List<MessageQueue> messages = messageQueueRepository.findByStatus(MessageStatus.NOTSENT);
			
			if (messages != null) {
				jobHistoryRepository.save(history);

				logger.info("Welcome Email Job Stated");

				for (MessageQueue message : messages) {
					User user = userRepository.findByEmail(message.getEmail()).get();
					try {
						Thread.sleep(10000);
					} catch (InterruptedException ex) {
						
						Thread.currentThread().interrupt();
						return;
					}
					
		          
		            
		            if(message.getPackageID() != null) {
		            	long ID = message.getPackageID();
		            	emailService.sendTemplate(user, message.getScheduledId().getTemplateName(),ID);
		            }else {
		            	emailService.sendTemplate(user, message.getScheduledId().getTemplateName(),0);
		            }
					
					
					message.setStatus(MessageStatus.SENT);
					message.setRundate(date);
					messageQueueRepository.save(message);

				}
				millis = System.currentTimeMillis();
				date = new java.sql.Date(millis);
				time = new java.sql.Time(millis);
				history.setRundate(date);
				history.setRuntime(time);

				history.setStatus(JobStatus.Passed);

				jobHistoryRepository.save(history);

			}

		} catch (Exception e) {

			millis = System.currentTimeMillis();
			date = new java.sql.Date(millis);
			time = new java.sql.Time(millis);
			history.setRundate(date);
			history.setRuntime(time);
			e.printStackTrace();
			history.setStatus(JobStatus.Failed);
			e.printStackTrace();
		}

		logger.info("Welcome Email Job End");
	}
	
	public void addPoint(Package package1) {
		// TODO Auto-generated method stub
		User user =userRepository.findById(package1.getUserId().getId()).get();
		
		Long point = (package1.getWeight()>=POINTSLIMIT?POINTSLIMIT:package1.getWeight()) + user.getPoints();
		
		user.setPoints(point);
		
		userRepository.save(user);
	}
	
	public void populatedQueueRepository() {
		List<Package> packages = packageepository.findByEmailSent(false);
		logger.info("populated Message Queue");
		for (Package items : packages) {
                  
			 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
		       if(TimeUnit.MILLISECONDS.toMinutes(timestamp.getTime() - items.getUpdate_at().getTime()) <=5 ) {
		    	
		    	   logger.info(TimeUnit.MILLISECONDS.toMinutes(timestamp.getTime() - items.getUpdate_at().getTime())+" "+items.getId() );
			    	
		   		MessageQueue queue = new  MessageQueue();
				ScheduledJob scheduledId = new ScheduledJob();
				long id=1l;
				
				if(items.getStatus()==PackagesStatus.Received_AT_Warehouse) {
					id= 7L;
				}else if(items.getStatus()==PackagesStatus.Ready_For_Pickup_Delivery){
					logger.info("Ajusting  Points for"+items.getUserid().getName() );
					addPoint(items);
					id= 8L;
				}else if(items.getStatus()==PackagesStatus.Processing_by_customs){
					id= 5L;
				}
				else if(items.getStatus()==PackagesStatus.Pending){
					id= 10L;
				}
				else if(items.getStatus()==PackagesStatus.Delayed){
					id= 11L;
				}
				else {
					break;
				}
				
				
				queue.setEmail(items.getUserid().getEmail());
				queue.setPhonenumber(null);
				queue.setRundate(null);
				queue.setScheduledId(scheduleRepository.getOne(id));
				queue.setStatus(MessageStatus.NOTSENT);
				queue.setPackageID(items.getId());
				messageQueueRepository.save(queue);
				
		       }
			 
		}
	}

}
