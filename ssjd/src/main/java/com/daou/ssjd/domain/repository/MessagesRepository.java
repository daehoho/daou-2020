package com.daou.ssjd.domain.repository;

import com.daou.ssjd.domain.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
}