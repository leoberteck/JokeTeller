package com.example.jokeprovider;

public final class Joke {

    private int id;
    private String content;

    public Joke() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Joke joke = (Joke) o;

        return getId() == joke.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
