package com.kovecmedia.redseat.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kovecmedia.redseat.entity.Invoice;

import com.kovecmedia.redseat.entity.Package;

public interface InvoiceRepository  extends JpaRepository<Invoice, Long> {
       List<Invoice> findByPackageId(Package package1);
}
