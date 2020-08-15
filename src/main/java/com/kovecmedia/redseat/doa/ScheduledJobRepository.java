package com.kovecmedia.redseat.doa;

import com.kovecmedia.redseat.entity.ScheduledJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledJobRepository extends JpaRepository<ScheduledJob, Long> {

}
