package programs;

import com.doje.app.console.NoticeConsole;

import java.sql.SQLException;

public class Program5 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        NoticeConsole console = new NoticeConsole();


        EXIT : while(true) {
            console.printNoticeList();
            int menu = console.inputNoticeMenu();
            switch (menu) {
                case 1://상세조회
                    break;
                case 2://이전
                    break;
                case 3://다음
                    break;
                case 4://글쓰기
                    break;
                case 5:
                    System.out.println("다음에 또 이용해 주세요~!");
                    break EXIT;
                default:
                    System.out.println("<<사용 방법>> 1~4사이의 숫자를 입력해 주세요");
                    break;

            }
        }

    }
}
