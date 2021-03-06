
> **why?** a push notification is the only way for a backend service to deliver info/data to users and/or its client app; otherwise the backend service would have to wait till it receives a request from a user to deliver that info/data

# push notifications

## [apple](https://github.com/vangav/vos_backend/tree/master/src/com/vangav/backend/push_notifications/apple)

+ enables sending push notifications to apple devices (iPhone, iPad, apple watch, ...)

### structure

| class | explanation |
| ----- | ----------- |
| [AppleNotification](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/push_notifications/apple/AppleNotification.java) | represents an apple push notification (to, alert, sound, ...) |
| [AppleNotificationSender](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/push_notifications/apple/AppleNotificationSender.java) | handles sending apple push notifications |
| [AppleNotificationProperties](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/push_notifications/apple/AppleNotificationProperties.java) | maps [apple_notification_properties.prop](https://github.com/vangav/vos_backend/blob/master/prop/apple_notification_properties.prop) properties file; make sure [`client_type`](https://github.com/vangav/vos_backend/blob/master/prop/apple_notification_properties.prop#L64) is set to `PRODUCTION` (with a production certificate) before releasing the app |
| [AppleNotificationDispatchable](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/push_notifications/apple/dispatch_message/AppleNotificationDispatchable.java) | is the dispatchable version of [AppleNotification](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/push_notifications/apple/AppleNotification.java); used to handle notification sending in the worker service |

### usage template

```java
  // init Apple push notification
  AppleNotification appleNotification =
    new AppleNotificationBuilder(deviceToken)
      .alertBody("usage template notification")
      .badgeNumber(1)
      .build();
    
  // option 1 - send it synchronously
  Pair<NotificationStatus, String> result =
    AppleNotificationSender.i().sendNotification(
      appleNotification);

  // option 2 - dispatch it to a worker instance to execute it

  AppleNotificationDispatchable appleNotificationDispatchable =
    new AppleNotificationDispatchable(appleNotification);
  
  request.getDispatcher()
    .addDispatchMessage(appleNotificationDispatchable);
```

### usage example

+ in [instagram / HandlerComment: `dispatchPushNotifications`](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/comment/HandlerComment.java#L284)

```java
  request.getDispatcher().addDispatchMessage(
    new AppleNotificationDispatchable(
      new AppleNotificationBuilder(deviceToken)
        .alertBody(commenterName + " commented on your photo")
        .badgeNumber(1)
        .build() ) );
```

## [android](https://github.com/vangav/vos_backend/tree/master/src/com/vangav/backend/push_notifications/android)

+ enables sending push notifications to android devices through gcm (google cloud messaging)

### structure

| class | explanation |
| ----- | ----------- |
| [AndroidNotification](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/push_notifications/android/AndroidNotification.java) | represents an android push notification (to, alert, sound, ...) |
| [AndroidNotificationSender](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/push_notifications/android/AndroidNotificationSender.java) | handles sending android push notifications |
| [AndroidNotificationProperties](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/push_notifications/android/AndroidNotificationProperties.java) | maps [android_notification_properties.prop](https://github.com/vangav/vos_backend/blob/master/prop/android_notification_properties.prop) properties file |
| [AndroidNotificationDispatchable](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/push_notifications/android/dispatch_message/AndroidNotificationDispatchable.java) | is the dispatchable version of [AndroidNotification](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/push_notifications/android/AndroidNotification.java); used to handle notification sending in the worker service |

### usage template
```java
  // init Android push notification
  AndroidNotification androidNotification =
    new AndroidNotification(
      new Message.Builder()
        .collapseKey("usage template notification")
        .build(),
      deviceToken);
    
  // option 1 - send it synchronously
  Result result =
    AndroidNotificationSender.i()
    .sendNotification(androidNotification);

  // option 2 - dispatch it to a worker instance to execute it

  AndroidNotificationDispatchable androidNotificationDispatchable =
    new AndroidNotificationDispatchable(androidNotification);
  
  request.getDispatcher()
    .addDispatchMessage(androidNotificationDispatchable);
```

### usage example

+ in [instagram / HandlerComment: `dispatchPushNotifications`](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/comment/HandlerComment.java#L294)

```java
  request.getDispatcher().addDispatchMessage(
    new AndroidNotificationDispatchable(
      new AndroidNotification(
        new Message.Builder()
          .collapseKey(commenterName + " commented on your photo")
          .build(),
        deviceToken) ) );
```

# next tutorial -> [security](https://github.com/vangav/vos_backend/tree/master/src/com/vangav/backend/security)
> handles authentication (facebook, google, oauth 2 and transaction tokens) and cryptography (asymmetric, hashing and two-way encryption)

# share

[![facebook share](https://www.prekindle.com/images/social/facebook.png)](https://www.facebook.com/sharer/sharer.php?u=https%3A//github.com/vangav/vos_backend)  [![twitter share](http://www.howickbaptist.org.nz/wordpress/media/twitter-64-black.png)](https://twitter.com/home?status=vangav%20backend%20%7C%20build%20big%20tech%2010x%20faster%20%7C%20https%3A//github.com/vangav/vos_backend)  [![pinterest share](http://d7ab823tjbf2qywyt3grgq63.wpengine.netdna-cdn.com/wp-content/themes/velominati/images/share_icons/pinterest-black.png)](https://pinterest.com/pin/create/button/?url=https%3A//github.com/vangav/vos_backend&media=https%3A//scontent-mad1-1.xx.fbcdn.net/v/t31.0-8/20645143_1969408006608176_5289565717021239224_o.png?oh=acf20113a3673409d238924cfec648d2%26oe=5A3414B5&description=)  [![google plus share](http://e-airllc.com/wp-content/themes/nebula/images/social_black/google.png)](https://plus.google.com/share?url=https%3A//github.com/vangav/vos_backend)  [![linkedin share](http://e-airllc.com/wp-content/themes/nebula/images/social_black/linkedin.png)](https://www.linkedin.com/shareArticle?mini=true&url=https%3A//github.com/vangav/vos_backend&title=vangav%20backend%20%7C%20build%20big%20tech%2010x%20faster&summary=&source=)

# free consulting

[![vangav's consultant](http://www.footballhighlights247.com/images/mobile-share/fb-messenger-64x64.png)](https://www.facebook.com/mustapha.abdallah)
