package PackageC;


public class OnePublicClassPerFile
{
    private int x;

    public OnePublicClassPerFile()
    {
        x = 0;
    }

    public int sampleMethod(int y)
    {
        return x + y;
    }
}


class PackageClass1
{
    public PackageClass1() {}
    public void hello() { System.out.println("Hello\n") ; }
}

class PackageClass2
{
    public PackageClass2() {}
    public void hello() { System.out.println("Hello\n") ; }
}