public interface TriggerInterface
{
    //Abstract unlock method to so that Lock class can work with any unlcok() from any Trigger class
    public void unlock(Object input);

    //Abstract lock method to so that Lock class can work with any unlcok() from any Trigger class
    public void lock(Object input);
}