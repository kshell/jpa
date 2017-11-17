package org.kshell.jpa.repository;

import org.kshell.jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * @author kshell
 * @version 1.0
 * create by 2017-11-10
 * describe:
 */
@org.springframework.stereotype.Repository
public interface PersonRepository extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person> {
    /**
     * 该方法不被eclipselink支持
     * @param lastNames
     * @return
     */
    List<Person> findByLastNameIn(List<String> lastNames);
}
