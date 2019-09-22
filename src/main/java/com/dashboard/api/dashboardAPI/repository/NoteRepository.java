package com.dashboard.api.dashboardAPI.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dashboard.api.dashboardAPI.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

}