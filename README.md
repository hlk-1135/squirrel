##squirrel校园二手交易平台

----------
**项目起源：**
期末的课程设计，三人一组，我和两个舍友，一时起兴，决定做一个校园二手交易平台，一开始兴致与激情满满，可是后期时间上的冲突，让我们三个人对于这个项目有点累。
这个项目刚定题两天，BezosLee由于通过了360的网上在线笔试，收到了360公司的免费培训邀请，要去北京的360总部进行培训一周，值得庆幸的是，最后培训完进行了面试并顺利拿到实习Offer。4月底，L_75进行了为期一周的ACM集训，备战5月9号的山东省ACM比赛，同样值得庆幸的是，作为他的退役赛，收获了一枚铜牌。而我，除了每天早上6点的排球训练备战5月底的校排球联赛，还要每天学习英语和高数，因为我选择了考研。
**不过我相信我们三个会做好，选我们所想，忠我们所爱。**

----------

**开发环境：** IntelliJ IDEA、Navicat for MySQL
**使用技术：**

 -  Spring+SpringMVC+Mybatis，Maven
 - JavaScript+Jquery+React


**开发人员：** [L-75](http://blog.csdn.net/llwwlql)、[HLK_1135](http://blog.csdn.net/HLK_1135)、[BezosLee](https://www.makeco.cn/)
**项目分工：** 按照功能进行模块化开发，每人负责的模块要兼顾前端+后台。
**项目源码：**[Squirrel校园二手交易](https://github.com/Squirrel-LDX)


----------

###**项目进度：**
####**2017/04/20**
对项目的需求进行简单的分析，由于财力原因，暂时放弃支付宝的在线支付，采用自主定制规则的线下交易。解决的难题：通过对商品设置生命周期实现商品的下线

![这里写图片描述](http://img.blog.csdn.net/20170511114537821?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSExLXzExMzU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


----------
####**2017/04/25**

对功能进行分析并进行模块化：

![这里写图片描述](http://img.blog.csdn.net/20170511115610612?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSExLXzExMzU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
完成数据库的设计文档：[squirrel数据库设计文档](https://book.makeco.cn/%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1.html)
![这里写图片描述](http://img.blog.csdn.net/20170511120215737?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSExLXzExMzU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)


----------
####**2017/04/28**
这学期课程学的是Struts2+Hibernate，但是不打算使用这两个框架。
决定使用SSM框架进行整合来开发项目。

**完成了开发环境的搭建。**

----------
####**2017/05/03**
完成数据库的设计

![这里写图片描述](http://img.blog.csdn.net/20170511121551605?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSExLXzExMzU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

----------
####**2017/05/08**
**HLK_1135:**
完成二手交易平台的首页、登录、注册的前端，仿[复旦二手工坊](http://www.fudan.market/)，后台稍微进行了的测试。对密码进行MD5加密。

----------
####**2017/05/10**
**重新修改数据库，取消外键，利用 Java代码实现表之间的逻辑关系。**
####**BezosLee:**
完成管理员的后台管理------[Github详细介绍](https://github.com/Squirrel-LDX/admin)

![这里写图片描述](http://img.blog.csdn.net/20170511120946666?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSExLXzExMzU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

####**HKL_1135：**
修改了登录注册的后台代码，完成了整个登录注册以及校验。
完成登录后个人中心信息的完善与修改页面，并实现后台代码
![这里写图片描述](http://img.blog.csdn.net/20170511121202760?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvSExLXzExMzU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

####**L_75：**
完成对二手商品模块数据的增删改查，并将**接口与数据**提供给**BezosLee、HLK_1135**使用。

----------
