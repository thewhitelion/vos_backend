/**
 * "First, solve the problem. Then, write the code. -John Johnson"
 * "Or use Vangav M"
 * www.vangav.com
 * */

/**
 * MIT License
 *
 * Copyright (c) 2016 Vangav
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 * */

/**
 * Community
 * Facebook Group: Vangav Open Source - Backend
 *   fb.com/groups/575834775932682/
 * Facebook Page: Vangav
 *   fb.com/vangav.f
 * 
 * Third party communities for Vangav Backend
 *   - play framework
 *   - cassandra
 *   - datastax
 *   
 * Tag your question online (e.g.: stack overflow, etc ...) with
 *   #vangav_backend
 *   to easier find questions/answers online
 * */

package com.vangav.backend.content.checking;

import com.vangav.backend.exceptions.CodeException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.exceptions.VangavException.ExceptionType;
import com.vangav.backend.exceptions.handlers.ArgumentsInl;

/**
 * @author mustapha
 * fb.com/mustapha.abdallah
 */
/**
 * CassandraCqlVerifierInl has inline static methods for verifying the
 *   correctness of Cassandra Cql
 * */
public class CassandraCqlVerifierInl {

  // disable default instantiation
  private CassandraCqlVerifierInl () {}
  
  /**
   * verifyName
   * throws an exception if name is:
   * - null
   * - empty
   * - longer than 32 characters
   * - starts with something other than a lower case letter, an upper case
   *     letter or an under score
   * - has characters other than lower case letters, upper case letters,
   *     underscores and digits
   * @param name
   * @throws Exception
   */
  public static void verifyName (String name) throws Exception {
    
    ArgumentsInl.checkNotEmpty(
      "Cassandra CQL name",
      name,
      ExceptionType.CODE_EXCEPTION);
    
    if (name.length() > 32) {
      
      throw new CodeException(
        23,
        1,
        "a name in Cassandra CQL must have at least one char and not more than"
        + "32 chars, current name["
        + name
        + "] has ["
        + name.length()
        + "] chars",
        ExceptionClass.FORMATTING);
    }
    
    if (CharVerifierInl.isOneOfChar(
          name.charAt(0),
          CharVerifierInl.CharType.LOWER_CASE,
          CharVerifierInl.CharType.UPPER_CASE,
          CharVerifierInl.CharType.UNDER_SCORE) == false) {
      
      throw new CodeException(
        23,
        2,
        "a name in Cassandra CQL must start with a lower case letter, "
        + "an upper case letter or an under score; invalid name ["
        + name
        + "] starts with ["
        + name.charAt(0)
        + "]",
        ExceptionClass.FORMATTING);
    }
    
    for (int i = 1; i < name.length(); i ++) {
      
      if (CharVerifierInl.isOneOfChar(
            name.charAt(i),
            CharVerifierInl.CharType.LOWER_CASE,
            CharVerifierInl.CharType.UPPER_CASE,
            CharVerifierInl.CharType.UNDER_SCORE,
            CharVerifierInl.CharType.DIGIT) == false) {
        
        throw new CodeException(
          23,
          3,
          "a name in Cassandra CQL can only contain a lower case letter, "
          + "an upper case letter, an under score or a digit; invalid name ["
          + name
          + "] has ["
          + name.charAt(i)
          + "]",
          ExceptionClass.FORMATTING);
      }
    }
  }
  
  /**
   * verifyColumnType
   * throws an exception is param type is an invalid Cassandra CQL column type
   * @param type
   * @throws Exception
   */
  public static void verifyColumnType (
    String type) throws Exception {
    
    ArgumentsInl.checkNotEmpty(
      "Cassandra CQL column type",
      type,
      ExceptionType.CODE_EXCEPTION);
    
    StringVerifierInl.isOneOfString(
      "Cassandra CQL column type",
      type,
      true,
      "ascii",
      "bigint",
      "blob",
      "boolean",
      "counter",
      "decimal",
      "double",
      "float",
      "frozen",
      "inet",
      "int",
      "text",
      "timestamp",
      "timeuuid",
      "tuple",
      "uuid",
      "varchar",
      "varint",

      "list<ascii>",
      "list<bigint>",
      "list<boolean>",
      "list<decimal>",
      "list<double>",
      "list<float>",
      "list<inet>",
      "list<int>",
      "list<text>",
      "list<timestamp>",
      "list<timeuuid>",
      "list<uuid>",
      "list<varchar>",
      "list<varint>",

      "map<ascii, ascii>",
      "map<ascii, bigint>",
      "map<ascii, boolean>",
      "map<ascii, decimal>",
      "map<ascii, double>",
      "map<ascii, float>",
      "map<ascii, inet>",
      "map<ascii, int>",
      "map<ascii, text>",
      "map<ascii, timestamp>",
      "map<ascii, timeuuid>",
      "map<ascii, uuid>",
      "map<ascii, varchar>",
      "map<ascii, varint>",
      
      "map<bigint, ascii>",      
      "map<bigint, bigint>",      
      "map<bigint, boolean>",      
      "map<bigint, decimal>",      
      "map<bigint, double>",      
      "map<bigint, float>",      
      "map<bigint, inet>",      
      "map<bigint, int>",      
      "map<bigint, text>",      
      "map<bigint, timestamp>",      
      "map<bigint, timeuuid>",      
      "map<bigint, uuid>",      
      "map<bigint, varchar>",      
      "map<bigint, varint>",
      
      "map<boolean, ascii>",
      "map<boolean, bigint>",
      "map<boolean, boolean>",
      "map<boolean, decimal>",
      "map<boolean, double>",
      "map<boolean, float>",
      "map<boolean, inet>",
      "map<boolean, int>",
      "map<boolean, text>",
      "map<boolean, timestamp>",
      "map<boolean, timeuuid>",
      "map<boolean, uuid>",
      "map<boolean, varchar>",
      "map<boolean, varint>",
      
      "map<decimal, ascii>",
      "map<decimal, bigint>",
      "map<decimal, boolean>",
      "map<decimal, decimal>",
      "map<decimal, double>",
      "map<decimal, float>",
      "map<decimal, inet>",
      "map<decimal, int>",
      "map<decimal, text>",
      "map<decimal, timestamp>",
      "map<decimal, timeuuid>",
      "map<decimal, uuid>",
      "map<decimal, varchar>",
      "map<decimal, varint>",
      
      "map<double, ascii>",      
      "map<double, bigint>",      
      "map<double, boolean>",      
      "map<double, decimal>",      
      "map<double, double>",      
      "map<double, float>",      
      "map<double, inet>",      
      "map<double, int>",      
      "map<double, text>",      
      "map<double, timestamp>",      
      "map<double, timeuuid>",      
      "map<double, uuid>",      
      "map<double, varchar>",      
      "map<double, varint>",
      
      "map<float, ascii>",
      "map<float, bigint>",
      "map<float, boolean>",
      "map<float, decimal>",
      "map<float, double>",
      "map<float, float>",
      "map<float, inet>",
      "map<float, int>",
      "map<float, text>",
      "map<float, timestamp>",
      "map<float, timeuuid>",
      "map<float, uuid>",
      "map<float, varchar>",
      "map<float, varint>",
      
      "map<inet, ascii>",
      "map<inet, bigint>",      
      "map<inet, boolean>",      
      "map<inet, decimal>",      
      "map<inet, double>",      
      "map<inet, float>",      
      "map<inet, inet>",      
      "map<inet, int>",      
      "map<inet, text>",      
      "map<inet, timestamp>",      
      "map<inet, timeuuid>",      
      "map<inet, uuid>",      
      "map<inet, varchar>",
      "map<inet, varint>",
      
      "map<int, ascii>",
      "map<int, bigint>",
      "map<int, boolean>",
      "map<int, decimal>",
      "map<int, double>",
      "map<int, float>",
      "map<int, inet>",
      "map<int, int>",
      "map<int, text>",
      "map<int, timestamp>",
      "map<int, timeuuid>",
      "map<int, uuid>",
      "map<int, varchar>",
      "map<int, varint>",
      
      "map<text, ascii>",
      "map<text, bigint>",
      "map<text, boolean>",
      "map<text, decimal>",
      "map<text, double>",
      "map<text, float>",
      "map<text, inet>",
      "map<text, int>",
      "map<text, text>",
      "map<text, timestamp>",
      "map<text, timeuuid>",
      "map<text, uuid>",
      "map<text, varchar>",
      "map<text, varint>",
      
      "map<timestamp, ascii>",
      "map<timestamp, bigint>",
      "map<timestamp, boolean>",
      "map<timestamp, decimal>",
      "map<timestamp, double>",
      "map<timestamp, float>",
      "map<timestamp, inet>",
      "map<timestamp, int>",
      "map<timestamp, text>",
      "map<timestamp, timestamp>",
      "map<timestamp, timeuuid>",
      "map<timestamp, uuid>",
      "map<timestamp, varchar>",
      "map<timestamp, varint>",
      
      "map<timeuuid, ascii>",
      "map<timeuuid, bigint>",
      "map<timeuuid, boolean>",
      "map<timeuuid, decimal>",
      "map<timeuuid, double>",
      "map<timeuuid, float>",
      "map<timeuuid, inet>",
      "map<timeuuid, int>",
      "map<timeuuid, text>",
      "map<timeuuid, timestamp>",
      "map<timeuuid, timeuuid>",
      "map<timeuuid, uuid>",
      "map<timeuuid, varchar>",
      "map<timeuuid, varint>",
      
      "map<uuid, ascii>",
      "map<uuid, bigint>",
      "map<uuid, boolean>",
      "map<uuid, decimal>",
      "map<uuid, double>",
      "map<uuid, float>",
      "map<uuid, inet>",
      "map<uuid, int>",
      "map<uuid, text>",
      "map<uuid, timestamp>",
      "map<uuid, timeuuid>",
      "map<uuid, uuid>",
      "map<uuid, varchar>",
      "map<uuid, varint>",
      
      "map<varchar, ascii>",
      "map<varchar, bigint>",
      "map<varchar, boolean>",
      "map<varchar, decimal>",
      "map<varchar, double>",
      "map<varchar, float>",
      "map<varchar, inet>",
      "map<varchar, int>",
      "map<varchar, text>",
      "map<varchar, timestamp>",
      "map<varchar, timeuuid>",
      "map<varchar, uuid>",
      "map<varchar, varchar>",
      "map<varchar, varint>",
      
      "map<varint, ascii>",
      "map<varint, bigint>",
      "map<varint, boolean>",
      "map<varint, decimal>",
      "map<varint, double>",
      "map<varint, float>",
      "map<varint, inet>",
      "map<varint, int>",
      "map<varint, text>",
      "map<varint, timestamp>",
      "map<varint, timeuuid>",
      "map<varint, uuid>",
      "map<varint, varchar>",
      "map<varint, varint>",

      "set<ascii>",
      "set<bigint>",
      "set<boolean>",
      "set<decimal>",
      "set<double>",
      "set<float>",
      "set<inet>",
      "set<int>",
      "set<text>",
      "set<timestamp>",
      "set<timeuuid>",
      "set<uuid>",
      "set<varchar>",
      "set<varint>");
  }
  
  /**
   * verifyCaching
   * throws an exception if param cachingType is an invalid Cassandra CQL
   *   caching type
   * @param cachingType
   * @throws Exception
   */
  public static void verifyCaching (
    String cachingType) throws Exception {
    
    ArgumentsInl.checkNotEmpty(
      "Cassandra CQL table caching type",
      cachingType,
      ExceptionType.CODE_EXCEPTION);
    
    StringVerifierInl.isOneOfString(
      "Cassandra CQL table caching type",
      cachingType,
      true,
      "ALL",
      "KEYS_ONLY",
      "NONE");
  }
  
  /**
   * verifyOrderByType
   * throws an exception if param orderByType is an invalid Cassandra CQL
   *   order by type
   * @param orderByType
   * @throws Exception
   */
  public static void verifyOrderByType (
    String orderByType) throws Exception {
    
    ArgumentsInl.checkNotEmpty(
      "Cassandra CQL table order by type",
      orderByType,
      ExceptionType.CODE_EXCEPTION);
    
    StringVerifierInl.isOneOfString(
      "Cassandra CQL table order by type",
      orderByType,
      true,
      "ASC",
      "DESC");
  }
}
