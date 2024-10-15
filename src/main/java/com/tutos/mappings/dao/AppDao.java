package com.tutos.mappings.dao;

import com.tutos.mappings.entities.Instructor;
import com.tutos.mappings.entities.InstructorDetails;

public interface AppDao {
    public void save(Instructor instructor);
    public Instructor findById(int id);
    public void deleteById(int id);
    public InstructorDetails findDetailsById(int id);
    public void deleteDetailsById(int id);

}
