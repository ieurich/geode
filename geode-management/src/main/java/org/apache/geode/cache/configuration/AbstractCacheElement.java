/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.geode.cache.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.StringUtils;

import org.apache.geode.annotations.Experimental;

@Experimental
public abstract class AbstractCacheElement implements CacheElement {
  protected List<String> groups = new ArrayList<>();

  /**
   * this returns a non-null value
   * for cluster level element, it will return "cluster" for sure.
   */
  @XmlTransient
  public String getConfigGroup() {
    String group = getGroup();
    if (StringUtils.isBlank(group)) {
      return "cluster";
    }
    return group;
  }

  /**
   * this returns the first group set by the user
   * if no group is set, this returns null
   */
  @XmlTransient
  public String getGroup() {
    if (groups.size() == 0) {
      return null;
    }
    return groups.get(0);
  }

  public void setGroup(String group) {
    groups.clear();

    if (StringUtils.isBlank(group)) {
      return;
    }
    groups.add(group);
  }
}
