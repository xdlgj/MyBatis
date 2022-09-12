import com.xdl.mapper.EmpMapper;
import com.xdl.pojo.Emp;
import com.xdl.pojo.EmpExample;
import com.xdl.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class GeneratorTest {
    /*
    @Test
    public void testMyBatis3Simple() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> emps = mapper.selectAll();
        System.out.println(emps);
    }
    */
    @Test
    public void testMyBatis3() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        EmpExample empExample = new EmpExample();
        //创建条件对象，通过andXXX方法为SQL添加查询添加，每个条件之间是and关系
        empExample.createCriteria().andEmpNameLike("a").andAgeGreaterThan(20).andDidIsNotNull();
        //将之前添加的条件通过or拼接其他条件
        empExample.or().andSexEqualTo("男");
        List<Emp> list = mapper.selectByExample(empExample);
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }
}
