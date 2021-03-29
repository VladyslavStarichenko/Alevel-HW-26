package lecture26.mongodb;

import com.mongodb.client.model.Filters;
import lecture26.mongodb.dao.UserDao;
import lecture26.mongodb.model.User;
import org.bson.conversions.Bson;

import java.util.List;

public class App {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        Bson titleAgeFilter = Filters.and(Filters.regex("title", "Java Dev"), Filters.lte("age", 21));
        Bson juniorFilter = Filters.regex("title", "Junior");
        Bson amazonOrgFilter = Filters.elemMatch("jobs", Filters.in("organization", "Amazon"));
        Bson numberOfJobsFilter = Filters.elemMatch("jobs", Filters.exists("jobs.3"));

        getInfoWithBson(userDao, juniorFilter);
        getInfoWithBson(userDao, titleAgeFilter);
        getInfoWithBson(userDao, amazonOrgFilter);
        getInfoWithBson(userDao, numberOfJobsFilter);


    }

    private static void getInfoWithBson(UserDao userDao, Bson filterTitleContainsJunior) {
        List<User> developers = userDao.findAllByFilter(filterTitleContainsJunior);
        System.out.println(developers);
    }


    private static User createUser() {
        User user = new User();
        user.setAge(25);
        user.setFullName("Bill White");
        user.setTitle("Senior Java Developer");
        return user;
    }
}
