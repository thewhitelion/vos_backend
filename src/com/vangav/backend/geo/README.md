
> **why?** geographical utilities are useful for many reasons like getting a coordinates country, city, ..., clustering the map into grids or geo hashes for indexing, ...

# geo

## structure

| class/pkg | explanation |
| --------- | ----------- |
| [EarthConstantsInl](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/EarthConstantsInl.java) | represents earth's geo-constants (i.e.: radius, latitude-range, map dimensions, ...) |
| [geo_grids](https://github.com/vangav/vos_backend/tree/master/src/com/vangav/backend/geo/geo_grids) | divides a map into square grids of any size and provide various operations on those grids like: coordinates to grid, grid to coordinates, multi-level surrounding grids, ... |
| [reverse_geo_coding](https://github.com/vangav/vos_backend/tree/master/src/com/vangav/backend/geo/reverse_geo_coding) | given a latitude-longitude pair it finds: continent name, continent code, country name, country code, major city name and city name |
| [geo_hash](https://github.com/vangav/vos_backend/tree/master/src/com/vangav/backend/geo/third_party/geo_hash) | represents the [geohash](https://en.wikipedia.org/wiki/Geohash)  geocoding system invented by "Gustavo Niemeyer" |

## [geo_grids](https://github.com/vangav/vos_backend/tree/master/src/com/vangav/backend/geo/geo_grids)

### structure
    
| class | explanation |
| ----- | ----------- |
| [GeoCoordinates](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoCoordinates.java) | represents a geo-coordinate point (latitude, longitude and altitude) |
| [GeoGrid](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java) | represents a single grid in a geo-grids system and has all the functionalities provided by [geo_grids](https://github.com/vangav/vos_backend/tree/master/src/com/vangav/backend/geo/geo_grids) |
| [GeoGridId](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGridId.java) | represents a [GeoGrid](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java) id (can also be used a hash-key like in a HashMap) |
| [GeoGridIndex2D](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGridIndex2D.java) | represents the 2-D index of a [GeoGrid](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java) in a gro-grids system |
| [GeoGridsConfig](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGridsConfig.java) | represents a geo-grids system's config |

### [GeoGridsConfig](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGridsConfig.java)

+ by default uses earth's geo-system from [EarthConstantsInl](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/EarthConstantsInl.java)
+ it provides the flexibility to work on sub-earth maps (e.g.: the map of Paris) or other cosmic objects' maps (e.g: mars, earth's moon, ...)
+ [`gridDimension`](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGridsConfig.java#L160) defines each grid's side (e.g.: 4.5 metres, 1.2 km, 2 miles, 15 feet, ...)
+ on initialization [`gridDimension`](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGridsConfig.java#L160) must be converted to metres, e.g.:

```java
  // init config
  GeoGridsConfig geoGridsConfig =
    new GeoGridsConfig(
      "Earth",
      new Distance(
        123.456,
        DistanceUnitType.NAUTICAL_MILE).getAs(DistanceUnitType.METRE) );
```

### [GeoGrid](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java)

+ [GeoGrid](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java) is the main [geo_grids](https://github.com/vangav/vos_backend/tree/master/src/com/vangav/backend/geo/geo_grids) class providing most of the functionalities as follows

| method | explanation |
| ------ | ----------- |
| [`isValid`](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java#L172) | checks if a `geo grid` is valid (an invalid `geo grid` can be initialized using the default constructor) |
| [`getCenterGeoCoordinates`](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java#L217) | get the coordinates at the grid's center |
| [`equal`](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java#L233) | checks for grids' config and id (returns true even if both grids were initialiazed using different coordinates belonging to the same grid); use [`getGeoCoordinates`](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java#L190) to compare coordinates |
| [`getDistance`](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java#L260) | returns the distance between the coordinates used to initialize both grids |
| [`getCentersDistance`](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java#L284) | returns the distance between the center coordinates of both grids |
| [`getLine`](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java#L308) | returns a [LineSegment](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/math/geometry/LineSegment.java) object based on the 2d-indicies of both grids |
| [`getSurroundingGrids ()`](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java#L330) | returns a 2d array of this grid and one-level of surrounding grids |
| [`getSurroundingGrids (levels)`](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java#L352) | returns a 2d array containing all of the n-levels (from param levels (e.g.: 2, 3, ...) ) of surrounding grids |
| [`getSurroundingGridsLevels (startLevel, endLevel)`](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java#L423) | returns a list of grids per each requested level |
| [`getSurroundingGridsLevel (level)`](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java#L473) | returns a list of grids for the requested level (e.g.: get the grids on level-4 only) |

### usage template

```java
  // init config
  GeoGridsConfig geoGridsConfig =
    new GeoGridsConfig(
      "Earth",
      new Distance(
        123.456,
        DistanceUnitType.NAUTICAL_MILE).getAs(DistanceUnitType.METRE) );

  // pick coordinates
  GeoCoordinates geoCoordinates =
    new GeoCoordinates(
      29.9792,
      31.1342);

  // init a geo grid
  GeoGrid geoGrid =
    new GeoGrid(
      geoGridsConfig,
      geoCoordinates);

  // print grid's id
  System.out.println(
    "Geo Grid Id: [" + geoGrid.getGeoGridId().getId() + "]" );
  // prints --> Geo Grid Id: [10665]

  // get level-2 surrounding grids
  ArrayList<GeoGrid> levelTwoGrids =
    geoGrid.getSurroundingGridsLevel(
      2,
      true);

  // print level-2 surrounding grids
  for (GeoGrid levelTwoGeoGrid : levelTwoGrids) {

    System.out.println(
      "  " + levelTwoGeoGrid.getGeoGridId().getId() );
  }
  // prints --> (shaped to visualize the level)
  //   10305  10306  10307  10308  10309
  //   10484                       10488
  //   10663        [10665]        10667
  //   10842                       10846
  //   11021  11022  11023  11024  11025
```

### usage examples

+ initializing [GeoGridsConfig](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGridsConfig.java) in [instagram / Constants](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/common/Constants.java#L258)

```java
  /**
   * kGeoGridsConfig defines the geo grids config
   */
  public static final GeoGridsConfig kGeoGridsConfig;
  static {
    
    try {
      
      kGeoGridsConfig =
        new GeoGridsConfig(
          "vos_instagram",
          new Distance(
            kGeoGridDimensionMetres,
            DistanceUnitType.METRE) );
    } catch (Exception e) {
      
      throw new CodeException(
        300,
        8,
        "Couldn't initialize kGeoGridsConfig: "
          + VangavException.getExceptionStackTrace(e),
        ExceptionClass.INITIALIZATION);
    }
  }
```

+ initializing [GeoCoordinates](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoCoordinates.java) in [instagram / HandlerPostPhoto: `processRequest`](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/post_photo/HandlerPostPhoto.java#L287)

```java
  GeoCoordinates postGeoCoordinates =
    new GeoCoordinates(
      requestPostPhoto.latitude,
      requestPostPhoto.longitude);
```

+ initializing [GeoGrid](https://github.com/vangav/vos_backend/blob/master/src/com/vangav/backend/geo/geo_grids/GeoGrid.java) in [instagram / HandlerPostPhoto: `processRequest`](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/post_photo/HandlerPostPhoto.java#L292)

```java
  GeoGrid postGeoGrid =
    new GeoGrid(
      Constants.kGeoGridsConfig,
      postGeoCoordinates);
```

## [reverse_geo_coding](https://github.com/vangav/vos_backend/tree/master/src/com/vangav/backend/geo/reverse_geo_coding)

+ used to convert coordinates (latitude, longitude) into continent name/code, country name/code, major city and city

### usage examples

+ usage template

```java
  ReverseGeoCode reverseGeoCode =
    ReverseGeoCoding.i().getReverseGeoCode(
      29.9792,
      31.1342);

  System.out.println(reverseGeoCode.toString() );
  // prints -->
  //   city (Al Jīzah)
  //   major city(Gizeh)
  //   country code(EG)
  //   country(Egypt)
  //   continent code(AF)
  //   continent(Africa)
```

+ in [geo server / HandlerReverseGeoCode: `processRequest`](https://github.com/vangav/vos_geo_server/blob/master/app/com/vangav/vos_geo_server/controllers/reverse_geo_code/HandlerReverseGeoCode.java#L111)

```java
  // get reverse geo code
  ReverseGeoCode reverseGeoCode =
    ReverseGeoCoding.i().getReverseGeoCode(
      requestReverseGeoCode.latitude,
      requestReverseGeoCode.longitude);
    
  // set response
  ((ResponseReverseGeoCode)request.getResponseBody() ).set(
    requestReverseGeoCode.latitude,
    requestReverseGeoCode.longitude,
    geoHash,
    reverseGeoCode.getCity(),
    reverseGeoCode.getMajorCity(),
    reverseGeoCode.getCountryCode(),
    reverseGeoCode.getCountry(),
    reverseGeoCode.getContinentCode(),
    reverseGeoCode.getContinent() );
```

+ in [instagram / HandlerPostPhoto: `dispatchAnalysis`](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/post_photo/HandlerPostPhoto.java#L446)

```java
  ReverseGeoCode reverseGeoCode =
    ReverseGeoCoding.i().getReverseGeoCode(
      requestPostPhoto.latitude,
      requestPostPhoto.longitude);

  postLocation = reverseGeoCode.getContinentCode();
```

## [geo_hash](https://github.com/vangav/vos_backend/tree/master/src/com/vangav/backend/geo/third_party/geo_hash)

+ a [Geohash](https://en.wikipedia.org/wiki/Geohash) lib developed by "Silvio Heuberger" and distributed under Apache License 2.0

+ usage example in [geo server / HandlerReverseGeoCode: `processRequest`](https://github.com/vangav/vos_geo_server/blob/master/app/com/vangav/vos_geo_server/controllers/reverse_geo_code/HandlerReverseGeoCode.java#L104)
```java
  // get geo hash
  String geoHash =
    GeoHash.geoHashStringWithCharacterPrecision(
      requestReverseGeoCode.latitude,
      requestReverseGeoCode.longitude,
      12);
```

# exercise

> what's the difference between `geo grids` and `geo hash`?

# next tutorial -> [ids](https://github.com/vangav/vos_backend/tree/master/src/com/vangav/backend/ids)
> handles uuid operations, sequential ids and twitter's snow flake ids

# share

[![facebook share](https://www.prekindle.com/images/social/facebook.png)](https://www.facebook.com/sharer/sharer.php?u=https%3A//github.com/vangav/vos_backend)  [![twitter share](http://www.howickbaptist.org.nz/wordpress/media/twitter-64-black.png)](https://twitter.com/home?status=vangav%20backend%20%7C%20build%20big%20tech%2010x%20faster%20%7C%20https%3A//github.com/vangav/vos_backend)  [![pinterest share](http://d7ab823tjbf2qywyt3grgq63.wpengine.netdna-cdn.com/wp-content/themes/velominati/images/share_icons/pinterest-black.png)](https://pinterest.com/pin/create/button/?url=https%3A//github.com/vangav/vos_backend&media=https%3A//scontent-mad1-1.xx.fbcdn.net/v/t31.0-8/20645143_1969408006608176_5289565717021239224_o.png?oh=acf20113a3673409d238924cfec648d2%26oe=5A3414B5&description=)  [![google plus share](http://e-airllc.com/wp-content/themes/nebula/images/social_black/google.png)](https://plus.google.com/share?url=https%3A//github.com/vangav/vos_backend)  [![linkedin share](http://e-airllc.com/wp-content/themes/nebula/images/social_black/linkedin.png)](https://www.linkedin.com/shareArticle?mini=true&url=https%3A//github.com/vangav/vos_backend&title=vangav%20backend%20%7C%20build%20big%20tech%2010x%20faster&summary=&source=)

# free consulting

[![vangav's consultant](http://www.footballhighlights247.com/images/mobile-share/fb-messenger-64x64.png)](https://www.facebook.com/mustapha.abdallah)
