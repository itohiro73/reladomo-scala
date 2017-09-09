/*
 * Copyright 2017 FOLIO Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.folio_sec.reladomo.scala_api

/**
  * Represents a Scala facade of Reladomo's MithraTransactionalObject.
  * The type must provide a MithraTransactionalObject which is consistent with the Scala object's state.
  */
trait NewTemporalTransactionalObject extends TemporalTransactionalObjectBase {

  def insertUntil(exclusiveUntil: Timestamp)(implicit tx: Transaction): Unit = {
    underlying.insertUntil(exclusiveUntil)
  }

  def cascadeInsertUntil(exclusiveUntil: Timestamp)(implicit tx: Transaction): Unit = {
    underlying.cascadeInsertUntil(exclusiveUntil)
  }

  def insertWithIncrement()(implicit tx: Transaction): Unit = {
    underlying.insertWithIncrement()
  }

  def insertWithIncrementUntil(exclusiveUntil: Timestamp)(implicit tx: Transaction): Unit = {
    underlying.insertWithIncrementUntil(exclusiveUntil)
  }

}
