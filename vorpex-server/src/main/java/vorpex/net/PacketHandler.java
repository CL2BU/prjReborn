package vorpex.net;

public abstract class PacketHandler
{
    public abstract void ParseIn(ReceivingFactory Main) throws Exception;
}
