package org.kshell.jpa.entity;

import lombok.extern.slf4j.Slf4j;

import org.kshell.jpa.repository.PersonRepository;
import org.kshell.jpa.service.PersonService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import org.testng.annotations.Test;

import java.util.List;

import javax.persistence.criteria.*;

/**
 * @author kshell
 * @version 1.0
 * create by 2017-11-10
 * describe:
 */
@Slf4j
public class SpringDataTest {
    private ApplicationContext context        = new ClassPathXmlApplicationContext("classpath:spring-bean.xml");
    private PersonRepository personRepository = (PersonRepository) context.getBean("personRepository");
    private PersonService personService       = context.getBean(PersonService.class);

    /**
     * 测试环境是否正确
     *
     * @throws Exception
     */
    @Test
    public void testJpa() throws Exception {}

    /**
     * 批量保存
     *
     * @throws Exception
     */
    @Test
    public void testSave() throws Exception {
        this.personService.init();
    }

    /**
     * 排序
     *
     * @throws Exception
     */
    @Test
    public void testSort() throws Exception {
        Sort.Order order        = new Sort.Order(Sort.Direction.ASC, "lastName");
        Sort sort               = new Sort(order);
        List<Person> personList = this.personRepository.findAll(sort);

        for (Person person : personList) {
            log.info(person.toString());
        }
    }

    /**
     * 分页
     *
     * @throws Exception
     */
    @Test
    public void testPage() throws Exception {
        Sort.Order order   = new Sort.Order(Sort.Direction.DESC, "lastName");
        Sort sort          = new Sort(order);
        int pageNumber     = 1;
        int pageSize       = 5;
        Pageable pageable  = new PageRequest(pageNumber, pageSize, sort);
        Page<Person> page1 = this.personRepository.findAll(pageable);

        log.info("总条数:" + page1.getTotalElements());
        log.info("总页数:" + page1.getTotalPages());
        log.info("当前页:" + (page1.getNumber() + 1));
        log.info("每页条数:" + page1.getSize());
        List<Person> personList = page1.getContent();

        for (Person person : personList) {
            log.info("记录:" + person);
        }
    }

    /**
     * 带查询条件的分页
     *
     * @throws Exception
     */
    @Test
    public void testSpecificationPage() throws Exception {
        int pageNumber                      = 0;
        int pageSize                        = 5;
        Pageable pageable                   = new PageRequest(pageNumber, pageSize);
        Specification<Person> specification = new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path path           = root.get("id");
                Predicate predicate = cb.gt(path, 16);

                return predicate;
            }
        };
        Page<Person> page1 = this.personRepository.findAll(pageable);

        log.info("总条数:" + page1.getTotalElements());
        log.info("总页数:" + page1.getTotalPages());
        log.info("当前页:" + (page1.getNumber() + 1));
        log.info("每页条数:" + page1.getSize());
        List<Person> personList = page1.getContent();

        for (Person person : personList) {
            log.info("记录:" + person);
        }
    }
}
