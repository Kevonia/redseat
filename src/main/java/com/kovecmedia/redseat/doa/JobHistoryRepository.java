package com.kovecmedia.redseat.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.JobHistory;

public interface JobHistoryRepository extends JpaRepository<JobHistory, Long>  {

}
