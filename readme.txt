1.使用eclipselink做为jpa实现产品时,需要提供persistence.xml配置
并在entityManagerFactory中使用persistenceUnitName方式指定
<property name="persistenceUnitName" value="eclipselink" />
persistence.xml范例:
    <properties>
        <property name="eclipselink.ddl-generation" value="create-or-extend-tables"/>
        <property name="eclipselink.ddl-generation.output-mode" value="database"/>
        <!-- eclipselink.weaving设为ture运行不成功, false,和static成功, 这是一个关键点 -->
        <property name="eclipselink.weaving" value="static" />
        <!-- 修改为FINE后，控制台会打印出执行的sql语句，方便调试, 可选值SERVER -->
        <property name="eclipselink.logging.level" value="FINE"/>
        <property name="eclipselink.jdbc.allow-native-sql-queries" value="true" />
        <!-- 定义主键 UUID -->
        <!--<property name="eclipselink.session.customizer" value="com.tgb.itoo.base.util.uuid.UUIDSequence" />-->
    </properties>
----------------------------------
2.实体主键注解差异(mysql,ID为int或Integer)
@GeneratedValue
@GeneratedValue(strategy = GenerationType.AUTO)
以上两种注解将会生成一张名为sequence的表用于记录主键,并且主键是全局共享.
每次主键取50个值以供使用,未用完的直接废弃
@GeneratedValue(strategy = GenerationType.IDENTITY)
使用这种方式将使用数据库的自动增长AUTO_INCREMENT
----------------------------------
3.注意:PersonRepository中的如下自定义借口方法不被eclipselink支持
List<Person> findByLastNameIn(List<String> lastNames);
