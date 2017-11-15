# 1.关于GreenDao数据库的使用
#### 第一步：

    apply plugin: 'com.android.application'
    apply plugin: 'org.greenrobot.greendao' // apply greendao plugin


    greendao {
    schemaVersion 1 //指定数据库schema版本号，迁移等操作会用到；
    daoPackage 'com.wmd.sqlitetest.edwardsqlitetest.gen'//通过gradle插件生成的数据库相关文件的包名，默认为你的entity所在的包名；
    targetGenDir 'src/main/java'//自定义生成数据库文件的目录，可以将生成的文件放到我们的java目录中，而不是build中，这样就不用额外的设置资源目录了。
    }


    dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])

    // add  greendao library
    compile 'org.greenrobot:greendao:3.2.2'
    }
    	

#### 第二步：
 
     buildscript {
    repositories {
        jcenter()
        mavenCentral() // add greendao repository
    }


    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.3'
        classpath 'org.greenrobot:greendao-gradle-plugin:3.2.2' // add greenDao-gradle plugin
       
      }
    }
    
#### 第三步：
 
先写好数据库的实体类（如下方代码），并用实体注解标记，写好后直接点击Android studio选项栏的Build————>Build project 这样就自动生成了三个类：GreenDao，DaoSession，XxxDao

    @Entity
     public class PersonBean {
    @Id(autoincrement = true)
    private long id;
    private String name;
    private String sex;
    private int age;
    }




# 2.关于GreenDao一些注解的说明

+ @Entity：将我们的java普通类变为一个能够被greenDAO识别的数据库类型的实体类;

+ @nameInDb：在数据库中的名字，如不写则为实体中类名

+ @Id：选择一个long/Long属性作为实体Id，并且Id必须是Long类型的。在数据库方面，它是主键。参数autoincrement是设置ID值自增；

+ @NotNull：使该属性在数据库端成为"NOTNULL"列。通常使用@NotNull标记原始类型（long，int，short，byte）是有意义的；

+ @Transient：表明这个字段不会被写入数据库，只是作为一个普通的java类字段，用来临时存储数据的，不会被持久化。




# 3.关于DaoMaster、DaoSession、XxxDao说明

+ DaoMaster：使用greenDAO的切入点。DaoMaster保存数据库对象（SQLiteDatabase）并管理特定模式的DAO类（而不是对象）。 它具有静态方法来创建表或将它们删除。 其内部类OpenHelper和DevOpenHelper是在SQLite数据库中创建模式的SQLiteOpenHelper实现。一个DaoMaster就代表着一个数据库的连接。

+ DaoSession：管理特定模式的所有可用DAO对象，您可以使用其中一个getter方法获取。 DaoSession还为实体提供了一些通用的持久性方法，如插入，加载，更新，刷新和删除。 DaoSession可以让我们使用一些Entity的基本操作和获取Dao操作类，DaoSession可以创建多个，每一个都是属于同一个数据库连接的。  

+ XxxDAO：数据访问对象（DAO）持续存在并查询实体。 对于每个实体，GreenDAO生成一个DAO。 它比DaoSession有更多的持久化方法，例如：count，loadAll和insertInTx。