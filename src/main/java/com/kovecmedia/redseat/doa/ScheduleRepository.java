package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.ScheduledJob;



public interface ScheduleRepository extends JpaRepository<ScheduledJob, Long>  {

}
