 1.使用openjpa2.2.2做为jpa实现产品时,需要提供persistence.xml配置
 并在entityManagerFactory中使用persistenceUnitName方式指定
 <property name="persistenceUnitName" value="openjpa_2_2_2" />
 persistence.xml范例:
    <persistence-unit name="openjpa_2_2_2" transaction-type="RESOURCE_LOCAL">
        <provider>org.apache.openjpa.persistence.PersistenceProviderImpl</provider>
        <properties>
            <!--自动建表-->
            <property name="openjpa.jdbc.SynchronizeMappings" value="buildSchema(ForeignKeys=true,PrimaryKeys=true,Indexes=true,schemaAction=refresh)"/>
            <!--不使用openjpa增强-->
            <property name="openjpa.ClassLoadEnhancement" value="false"/>
            <property name="openjpa.DynamicEnhancementAgent" value="false"/>
            <property name="openjpa.RuntimeUnenhancedClasses" value="supported"/>
        </properties>
    </persistence-unit>
 ----------------------------------
 2.实体主键注解差异(mysql,ID为int或Integer)
 @GeneratedValue
 @GeneratedValue(strategy = GenerationType.AUTO)
 以上两种注解将会生成一张名为openjpa_sequence_table的表用于记录主键,并且主键是全局共享.
 每次主键取50个值以供使用,未用完的直接废弃
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 使用这种方式将使用数据库的自动增长AUTO_INCREMENT

