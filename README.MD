# JEvent

An Event and Listener API for Java

## Maven Installation

```maven

<repository>
  <id>reposilite-repository</id>
  <name>Seby's Project Repository</name>
  <url>http://mcsilent.de:8091/{repository}</url>
</repository>

<dependency>
  <groupId>com.github.sebyplays</groupId>
  <artifactId>JEvent</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

## Used libraries

- [lombok](https://projectlombok.org/)

## Usage/Examples

Call an Event:

```java
import com.github.sebyplays.jevent.JEvent;

public class ProjectThatNeedsEvents {

    public static void main(String[] args) {

        //register a listener
        new JEvent(new ExampleEvent()).registerListener(new TestListener());

        //call an event and handle cancellation
        ExampleEvent eEvent;
        if (!(eEvent = (ExampleEvent) new JEvent(new ExampleEvent("Johnny", 19)).callEvent().getEvent())
                .isCancelled()) {
            // Do some stuff here
        } else {
            //And do some stuff here
        }
    }

}
```

Create an Event:

```java
import com.github.sebyplays.jevent.api.Event;
import lombok.Getter;

public class ExampleEvent extends Event {

    @Getter
    private String name;
    @Getter
    private int age;

    public ExampleEvent() {
    }

    public ExampleEvent(String name, int age) {
        this.age = age;
        this.name = name;
    }

}
```

Create a Listener:

```java
import com.github.sebyplays.jevent.api.EventHandler;
import com.github.sebyplays.jevent.api.Listener;

public class TestListener implements Listener {

    @EventHandler
    public void onEvent(ExampleEvent event) {
        System.out.println(event.getAge() + " " + event.getName());
    }

}
```

