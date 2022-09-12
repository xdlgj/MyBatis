import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdl.mapper.EmpMapper;
import com.xdl.pojo.Emp;
import com.xdl.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class PageTest {
    @Test
    public void testPage() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        // 开启分页功能
        PageHelper.startPage(1, 2);
        List<Emp> empList = mapper.selectByExample(null);
        empList.forEach(emp -> System.out.println(emp));
        /**
         * 1、先查询总记录数
         *      SELECT count(0) FROM t_emp
         * 2、分页查询
         *  select eid, emp_name, age, sex, email, did from t_emp LIMIT ?, ?
         */
        PageInfo pageInfo = new PageInfo<>(empList, 3);
        System.out.println(pageInfo);

    }
}
