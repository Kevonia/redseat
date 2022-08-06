package com.kovecmedia.redseat.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.kovecmedia.redseat.doa.PackageRepository;
import com.kovecmedia.redseat.entity.Package;
import com.kovecmedia.redseat.payload.respond.PackageLabel;


@Service
public class BarCodeServiceImpl  {


	
	@Autowired
	PackageRepository packageRepository;
	
	
	
	public PackageLabel genarateLable(long id) {
		
		PackageLabel label =new PackageLabel();
		
		Package  package1= packageRepository.findById(id).get();
		
		label.setPackageDescrtion(package1.getDescription());
		label.setSeller(package1.getSeller());
		label.setWeigth(package1.getWeight()); 
		label.setLabelDate(new Date());
		
		label.setLocalAddress("17 Campbell Blvd, Kingston 11");
		label.setLocalEmail("info@rscja.com");
		label.setLocalPhone("(876) 512-2764");
		
		
		label.setFromName("Red Seat Courier Ltd");
		label.setFromAddressline1("6858 NW 75th Street");
		label.setFromAddressline2("Miami, Floria 33166");
		
		label.setToName("Red Seat Courier Ltd");
		label.setToUser(package1.getUserid().getName());
		
		
		label.setToName("Red Seat Courier Ltd");
		label.setToUser(package1.getUserid().getName());
		
		try {
			label.setQrTop( getQRCodeImage("https://www.kovecmedia.com/",300,300) );
		} catch (WriterException | IOException e) {
			
			e.printStackTrace();
		}
		try {
			label.setQrPackage( getQRCodeImage("https://dashboard.rscja.com/package/"+ new String(Base64.getEncoder().encode(package1.getId().toString().getBytes())),500,500) );
		} catch (WriterException | IOException e) {
			
			e.printStackTrace();
		}
		return label;
		
	}
	
	public static byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
	    QRCodeWriter qrCodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
	    
	    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	    byte[] pngData = pngOutputStream.toByteArray(); 
	    return pngData;
	}
}
