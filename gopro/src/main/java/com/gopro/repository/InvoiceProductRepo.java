package com.gopro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gopro.bene.InvoiceProductMap;

public interface InvoiceProductRepo extends JpaRepository<InvoiceProductMap, Long> {

	
}
