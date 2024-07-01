package vm;

public class Instruction
{
    static final int len = 10;
    private final ByteCode opcode;
    private final int[] args;

    public Instruction(ByteCode opcode)
    {
        this.opcode = opcode;
        this.args = null;
    }

    public Instruction(ByteCode opcode, int... args)
    {
        this.opcode = opcode;
        this.args = args;
    }

    void print(int ip)
    {
        System.err.printf("%04d: %-7s", ip, opcode);

        if (args != null)
        {
            for (int i : args)
                System.err.printf("%-2d ", i);

            System.err.printf("%" + (len - 3 * args.length) + "s", " ");
        }
        else
            System.err.printf("%" + len + "s", " ");
    }

    public ByteCode getOpcode()
    {
        return opcode;
    }

    public int[] getArgs()
    {
        return args;
    }
}
