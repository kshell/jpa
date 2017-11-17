 1.使用openjpa2.4.x做为jpa实现产品时,需要提供persistence.xml配置
 并在entityManagerFactory中使用persistenceUnitName方式指定
 <property name="persistenceUnitName" value="openjpa_2_4_x" />
 persistence.xml范例:
    <persistence-unit name="openjpa_2_4_x" transaction-type="RESOURCE_LOCAL">
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
 ----------------------------------
 3.openjpa2.2.2可以配置openjpa不增强.
 openjpa2.4.x必须要对实体增强,方法如下(参考官方文档):
 方法1:java org.apache.openjpa.enhance.PCEnhancer org.kshell.jpa.entity.Person
 方法2:使用maven
             <plugin>
                 <groupId>org.apache.openjpa</groupId>
                 <artifactId>openjpa-maven-plugin</artifactId>
                 <version>2.4.2</version>
                 <configuration>
                     <includes>**/entity/*.class</includes>
                     <excludes>**/entity/XML*.class</excludes>
                     <addDefaultConstructor>true</addDefaultConstructor>
                     <enforcePropertyRestrictions>true</enforcePropertyRestrictions>
                 </configuration>
                 <executions>
                     <execution>
                         <id>enhancer</id>
                         <phase>process-classes</phase>
                         <goals>
                             <goal>enhance</goal>
                         </goals>
                     </execution>
                 </executions>
                 <dependencies>
                     <dependency>
                         <groupId>org.apache.openjpa</groupId>
                         <artifactId>openjpa-all</artifactId>
                         <version>2.4.2</version>
                     </dependency>
                 </dependencies>
             </plugin>
mvn openjpa:enhance

