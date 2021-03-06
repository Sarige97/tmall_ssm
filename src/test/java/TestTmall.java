import com.sarige.tmall.util.Page;
import com.sarige.tmall.util.UrlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestTmall {

    @Test
    public void sout() {
        System.out.println("中文");
    }

    @Test
    public void test() {
        UrlBuilder urlBuilder = new UrlBuilder("www.baidu.com");
        urlBuilder.addParam("name", "tom");
        urlBuilder.addParam("age", "13");
        System.out.println(urlBuilder.toString());
    }

    @Test
    public void test3() {
        Log log = LogFactory.getLog(TestTmall.class);
        log.info("1234567");
    }

    public static void main(String[] args) {
        //准备分类测试数据：

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tmall_ssm?useUnicode=true&characterEncoding=utf8",
                        "root", "admin");
                Statement s = c.createStatement();
        ) {
            for (int i = 1; i <= 10; i++) {
                String sqlFormat = "insert into category values (null, '测试分类%d')";
                String sql = String.format(sqlFormat, i);
                s.execute(sql);
            }

            System.out.println("已经成功创建10条分类测试数据");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
