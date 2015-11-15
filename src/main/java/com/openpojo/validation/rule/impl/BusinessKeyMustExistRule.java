/*
 * Copyright (c) 2010-2015 Osman Shoukry
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.openpojo.validation.rule.impl;

import com.openpojo.business.cache.BusinessKeyField;
import com.openpojo.reflection.PojoClass;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.rule.Rule;

import static com.openpojo.business.utils.BusinessPojoHelper.getBusinessKeyFields;

/**
 * This rule ensures that PojoClass declares at least one required {@link com.openpojo.business.annotation.BusinessKey}.<br>
 * Required BusinessKey means either the required = true, or composite = true.
 *
 * @author oshoukry
 */
public class BusinessKeyMustExistRule implements Rule {

  public void evaluate(final PojoClass pojoClass) {
    for (BusinessKeyField businessField : getBusinessKeyFields(pojoClass.getClazz())) {
      if (businessField.isRequired() || businessField.isComposite()) {
        return;
      }
    }

    Affirm.fail(String.format("[%s] doesn't declare any 'required' BusinessKeys!!", pojoClass.getClazz()));
  }
}
