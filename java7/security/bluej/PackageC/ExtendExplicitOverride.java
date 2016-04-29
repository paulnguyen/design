package PackageC;

public class ExtendExplicitOverride extends OkToExtend
{
    private int x;

    public ExtendExplicitOverride()
    {
        x = 0;
    }

    @Override
    public int sampleMethod(int y)
    {
        return x + y;
    }
}
