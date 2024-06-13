package com.SL.DMSApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SL.DMSApplication.model.UploadFile;

@Repository
public interface UploadFileRepository extends JpaRepository<UploadFile, Long>{

}
