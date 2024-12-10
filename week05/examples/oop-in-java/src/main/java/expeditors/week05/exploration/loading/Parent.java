package expeditors.week05.exploration.loading;

public class Parent {
    public Parent() {
        System.out.println("Parent constructor executed...");
    }

    static {
        System.out.println("Parent static block executed...");
    }

    {
        System.out.println("Parent non-static block executed...");
    }

    public static void ParentMethod() {
        System.out.println("Parent static method executed...");
    }
}
