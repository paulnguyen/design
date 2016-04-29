package PackageC;

public class FinalMethod
{
    private int x;

    public FinalMethod()
    {
        x = 0;
    }

    public final int sampleMethod(int y)
    {
        return x + y;
    }
}
