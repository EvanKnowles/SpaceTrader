package za.co.knonchalant.space.domain;

public interface IPaged<T> {
    public T getData();
    public Meta getMeta();
}
