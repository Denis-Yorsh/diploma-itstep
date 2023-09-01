package org.itstep.diploma.contact.message.repository;

import org.itstep.diploma.contact.message.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}
