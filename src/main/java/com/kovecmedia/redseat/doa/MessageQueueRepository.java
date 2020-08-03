package com.kovecmedia.redseat.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.MessageQueue;
import com.kovecmedia.redseat.model.MessageStatus;

public interface MessageQueueRepository extends JpaRepository<MessageQueue, Long> {

	List<MessageQueue> findByStatus(MessageStatus status);
}
