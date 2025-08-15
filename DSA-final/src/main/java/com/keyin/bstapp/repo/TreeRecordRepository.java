package com.keyin.bstapp.repo;

import com.keyin.bstapp.entity.TreeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeRecordRepository extends JpaRepository<TreeRecord, Long> {
}
