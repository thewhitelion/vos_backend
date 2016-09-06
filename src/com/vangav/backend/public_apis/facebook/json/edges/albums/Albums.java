/**
 * "First, solve the problem. Then, write the code. -John Johnson"
 * "Or use Vangav M"
 * www.vangav.com
 * */

/**
 * no license, I know you already got more than enough to worry about
 * keep going, never give up
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

package com.vangav.backend.public_apis.facebook.json.edges.albums;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vangav.backend.public_apis.facebook.json.edges.edge.FacebookGraphApiEdge;

/**
 * @author mustapha
 * fb.com/mustapha.abdallah
 */
/**
 * Albums represents Facebook's graph API edge albums
 * 
 * Reference:
 * https://developers.facebook.com/docs/graph-api/reference/v2.7/user
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Albums extends FacebookGraphApiEdge {

  @Override
  @JsonIgnore
  protected String getName () throws Exception {
    
    return "albums";
  }
  
  @Override
  @JsonIgnore
  protected Albums getThis () throws Exception {
    
    return this;
  }

  @Override
  @JsonIgnore
  public String getEdgeName () throws Exception {
    
    return "albums";
  }
  
  @JsonProperty
  public Album [] data;
}
