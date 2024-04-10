package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService(); //클래스 레벨에 하나만 올라감

    //조회할때
    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){
        //private으로 설정해서 new를 막음
        //생성할 수 있는 곳은 없음
    }

    public void login(){
        System.out.println("싱글톤 객체 로직 호출");
    }

    public static void main(String[] args) {
        SingletonService singletonService1 = new SingletonService(); //NEW..
        SingletonService singletonService2 = new SingletonService();

    }
}
