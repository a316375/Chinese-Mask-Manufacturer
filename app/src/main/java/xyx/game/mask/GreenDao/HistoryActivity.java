package xyx.game.mask.GreenDao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import xyx.game.mask.Obj.User;
import xyx.game.mask.R;

public class HistoryActivity extends AppCompatActivity {

    private UsersDao usersDao;

    int i=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DaoSession daoSession = ((GreenDaoApplication)getApplication()).getDaoSession();
                usersDao = daoSession.getUsersDao();
                //add();
               // search();
                i++;

            }
        });
    }

//    private void search() {
//        String email = "123456789@qq.com";
//        String firstname = "John";
//
//// Single user query WHERE email matches "jdoe@example.com"
//        Users user = usersDao.queryBuilder()
//                .where(UsersDao.Properties.Email.eq(email)).build().unique();
//      // if (user!=null)Log.v("--User",user.toString());
//
//// Multiple user query WHERE firstname = "John"
//        List<Users> user1 = usersDao.queryBuilder()
//                .where(UsersDao.Properties.Firstname.eq(firstname)).build().list();
//
//
//
////        List<Users> joes = usersDao.queryBuilder().list();
////        List<Users> joes = getAllCategory();
////        for (int j = 0; j < joes.size(); j++) {
////            Log.v("--User--",joes.get(j).getEmail()+joes.size());
////        }
////
////        for (Users users:joes){
////            Log.v("-++User--",users.getEmail()+joes.size());
////        }
//
//
//        Iterator<Users> iterator1 = getAllCategory().iterator();
//        for (Iterator<Users> it = iterator1; it.hasNext(); ) {
//            Users user2 = it.next();
//            Log.v("-+///+User--",user2.getEmail());
//        }
//
//
//    }

//    private void add() {
//        Users users=new Users();
//        users.setEmail("1111@qq.com" );
//        users.setFirstname("123");
//        users.setLastname("456");
//        users.setId(Long.valueOf("111111"));
//        usersDao.insert(users);
//
//    }




    public ArrayList<Users > getAllCategory () {

        return new ArrayList<>(usersDao .loadAll());
    }
}