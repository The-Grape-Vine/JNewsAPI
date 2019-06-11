[![Download](https://api.bintray.com/packages/thevineyard/JNewsAPI/JNewsAPI/images/download.svg) ](https://bintray.com/thevineyard/JNewsAPI/JNewsAPI/_latestVersion)
# JNewsAPI
A Java/JVM wrapper for [newsapi.org](https://newsapi.org).

## Documentation
Javadoc is yet to be released.

## Usage
First grab a key from the [account page](https://newsapi.org/account).
```java
import xyz.thegrapevine.jnewsapi.*;

public class Example {
    public static void main(String[] args) {
        NewsClient client = new NewsClient("key");
        
        // Sources
        SourcesResponse response = client.sources();
        
        // Top headlines querying with "bitcoin"
        TopHeadlinesResponse headlines = client.topHeadlines(new HTTPParameter("q", "bitcoin"));
        
        // Everything querying CNN
        EverythingResponse everything = client.everything("sources", "cnn");
    }
}
```

## Get the library
### Maven

```xml
<repositories>
    <repository>
      <id>jcenter</id>
      <url>https://jcenter.bintray.com/</url>
    </repository>
</repositories>
```

```xml
<dependency>
    <groupId>xyz.thegrapevine</groupId>
    <artifactId>JNewsAPI</artifactId>
    <version>2.0.0</version>
    <type>pom</type>
</dependency>
```

### Gradle

```groovy
repositories {
    jcenter()
}

dependencies {
    implementation "xyz.thegrapevine:JNewsAPI:2.0.0"
}
```

## License
This library is licensed under the [Apache 2.0](https://github.com/The-Grape-Vine/JNewsAPI/blob/master/LICENSE) license.