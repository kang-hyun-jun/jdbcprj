package com.doje.app.console;

import com.doje.app.entity.Notice;
import com.doje.app.service.NoticeService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class NoticeConsole {

    private NoticeService noticeService;
    private int page;
    private String search_string;
    private String search_method;
    private int detaile_id;

    public NoticeConsole() {
        this.noticeService = new NoticeService();
        this.page = 1;
        this.search_string = "";
        this.search_method = "TITLE";
        this.detaile_id = -1;
    }

    public void printNoticeList() throws SQLException, ClassNotFoundException {
        List<Notice> list;
        int count = noticeService.getCount(page,search_method,search_string);
        if(detaile_id==-1)
        {
            list = noticeService.getlist(page,search_method,search_string);
        }
        else
        {
            count =1;
            list = noticeService.getlist(page,detaile_id);
        }

        int lastPage = (count - 1) / 10 + 1;

        System.out.print("─────────────────────────────────────────────\n");
        System.out.printf("<공지사항> 총 %d 게시글\n",count);
        System.out.print("─────────────────────────────────────────────\n");
        for(Notice notice : list) {
            if(detaile_id==-1)
            {
                System.out.printf("%d. %s / %s / %s \n",notice.getId(),notice.getTitle(),notice.getWriter_id(),notice.getDate());
            }
            else
            {
                System.out.println("              <<상세조회 내역>> ");
                System.out.printf("%d. %s \n",notice.getId(),notice.getTitle());
                System.out.printf("[작성자] : %s \n",notice.getWriter_id());
                System.out.printf("%s \n",notice.getContent());
                System.out.printf("[작성일자] : %s \n",notice.getDate());
                System.out.printf("[조회수] : %s \n",notice.getHit());
                System.out.printf("[첨부파일] : %s \n",notice.getFiles());
            }
        }
        System.out.print("─────────────────────────────────────────────\n");
        System.out.printf("              %d/%d pages\n",page,lastPage);
        detaile_id=-1;
    }
    public int inputNoticeMenu()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기/ 5.검색/ 6.모두보기/ 7.종료 >");
        String menu_string= scanner.nextLine();
        int menu_int = Integer.parseInt(menu_string);
        return menu_int;
    }

    public void movePrevList()
    {
        if(page==1)
        {
            System.out.println("===========================");
            System.out.println("이전 페이지가 존재하지 않습니다.");
            System.out.println("===========================");
            return;
        }
        page --;

    }
    public void moveNextList() throws SQLException, ClassNotFoundException {
        int count = noticeService.getCount(page,search_method,search_string);
        int lastPage = (count - 1) / 10 + 1;
        if(page==lastPage)
        {
            System.out.println("===========================");
            System.out.println("다음 페이지가 존재하지 않습니다.");
            System.out.println("===========================");
            return;
        }
        page++;
    }
    public void printNoticeAll()
    {
        this.search_string = "";
        page = 1;
    }
    public void search()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("─────────────────────────────────────────────\n");
        EXIT: while(true)
        {
            System.out.println("검색할 방식을 입력하세요.");
            System.out.print("1.제목/ 2.작성자/ 3.내용 >");
            String search_method_string = scanner.nextLine();
            int search_method_int = Integer.parseInt(search_method_string);
            switch (search_method_int) {
                case 1:
                    search_method="TITLE";
                    break EXIT;
                case 2:
                    search_method="WRITER_ID";
                    break EXIT;
                case 3:
                    search_method="WRITER_ID";
                    break EXIT;
                default:
                    System.out.println("===========================");
                    System.out.println("올바른 검색 방법을 선택하세요.");
                    System.out.println("===========================");
                    break;
            }
        }
        System.out.print("검색할 내용을 입력하세요. >");
        search_string = scanner.nextLine();
        page=1;//검색 시 첫 페이지로 이동;
        System.out.print("─────────────────────────────────────────────\n");
    }
    public void printNoticeDetaile() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String detaile_string;
        int detaile_int =-1;
        EXIT:while(true)
        {
            EXIT1:while(true)
            {
                System.out.print("─────────────────────────────────────────────\n");
                System.out.print("상세조회 하고 싶은 글의 ID를 입력하세요.>");
                detaile_string = scanner.nextLine();
                try{
                    detaile_int = Integer.parseInt(detaile_string);
                    break EXIT1;
                }
                catch (NumberFormatException ex){
                    System.out.println("===========================");
                    System.out.println("올바른 id를 선택하세요.");
                    System.out.println("===========================");

                }
            }
            if(!(noticeService.searchTo_id(detaile_int)))
            {
                System.out.println("===========================");
                System.out.println("해당 id의 게시물은 존재하지 않습니다.");
                System.out.println("===========================");
                break EXIT;
            }
            else
            {
                this.detaile_id=detaile_int;
                page=1;
                break EXIT;
            }
        }
    }
    public void writer() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String title_string;
        String writerid_string;
        String content_string;
        String files_string;
        System.out.print("─────────────────────────────────────────────\n");
        EXIT:while(true) {
            System.out.print("글의 제목을 입력하세요.>");
            title_string = scanner.nextLine();
            if(title_string.equals("")) {
                System.out.print("글의 제목은 필수로 입력해야 합니다.>");
            }
            else { break EXIT; }
        }
        EXIT:while(true) {
            System.out.print("글의 내용을 입력하세요.>");
            content_string = scanner.nextLine();
            if(content_string.equals("")) {
                System.out.print("글의 내용은 필수로 입력해야 합니다.>");
            }
            else { break EXIT; }
        }
        EXIT:while(true) {
            System.out.print("작성자의 ID를 입력하세요.>");
            writerid_string = scanner.nextLine();
            if(writerid_string.equals("")) {
                System.out.print("작성자의 ID는 필수로 입력해야 합니다.>");
            }
            else { break EXIT; }
        }
        System.out.print("첨부할 파일을 입력하세요.>");
        files_string = scanner.nextLine();

        Notice notice = new Notice();
        notice.setTitle(title_string);
        notice.setContent(content_string);
        notice.setWriter_id(writerid_string);
        notice.setFile(files_string);
        noticeService.insert(notice);


        }
}
