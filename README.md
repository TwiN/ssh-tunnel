
## Basic configuration

```java
TunnelConfiguration config = new TunnelConfiguration();
config.setServerIp("12.34.56.78");
config.setSshUsername("root");
config.setServerPort(3306);
config.setLocalPort(3366);
```


### With a ssh key

```java
config.setSshPrivateKey(new File("C:\\path\\to\\your\\ssh\\key.pem"));
```

### With a password

```java
config.setSshPassword("password123");
```


