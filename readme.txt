1.不配置persistence.xml
可以在entityManagerFactory中配置hibernate属性
<property name="jpaProperties">
    <props>
        <prop key="hibernate.ejb.naming_strategy">org.hibernate.cfg.ImprovedNamingStrategy</prop>
        <prop key="hibernate.dialect">org.hibernate.dialect.MySQL5Dialect</prop>
        <prop key="hibernate.show_sql">true</prop>
        <prop key="hibernate.format_sql">true</prop>
        <prop key="hibernate.hbm2ddl.auto">update</prop>
    </props>
</property>

2.配置persistence.xml
在entityManagerFactory中制定persistenceUnitName
<property name="persistenceUnitName" value="hibernate" />
----------------------------
Person实体ID配置(针对mysql数据库,主键为int或Integer类型)
@GeneratedValue
@GeneratedValue(strategy = GenerationType.AUTO)
@GeneratedValue(strategy = GenerationType.IDENTITY)
以上三种注解都可以正常识别为AUTO_INCREMENT(自动增长)