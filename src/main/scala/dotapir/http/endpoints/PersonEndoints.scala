package dotapir.http.endpoints

import sttp.tapir.*
import zio.*
import sttp.tapir.*
import sttp.tapir.json.zio.*
import sttp.tapir.generic.auto.*

import dotapir.model.*

object PersonEndpoints extends BaseEndpoint:

  /** Notes:
    *
    * only in swagger
    *   - the `tag` is the category of the endpoint, should be the same for all
    *   - the `name` is the name of the endpoint, should be unique by endpoint
    *
    * swagger + http api
    *   - the `in` corresponds to every path/body/... that can be given to an
    *     endpoint
    *   - the `out` corresponds to everything that can be returned by the
    *     endpoint
    */

  val createEndpoint: Endpoint[Unit, Person, Throwable, User, Any] =
    baseEndpoint
      .tag("persons")
      .name("create-person")
      .post
      .in("persons")
      .in(
        jsonBody[Person]
          .description("Person to create")
          .example(Person("John", 30, "jonh@dev.com"))
      )
      .out(jsonBody[User])
      .description("Create a person")

  val getByIdEndpoint: Endpoint[Unit, Long, Throwable, User, Any] =
    baseEndpoint
      .tag("persons")
      .name("get-person-by-id")
      .get
      .in("persons")
      .in(
        path[Long]("id")
          .description("ID of the person to get")
          .example(1)
      )
      .out(jsonBody[User])
      .description("Get a person by ID")

  val deleteEndpoint: Endpoint[Unit, Long, Throwable, User, Any] =
    baseEndpoint
      .tag("persons")
      .name("delete-person")
      .delete
      .in("persons")
      .in(
        path[Long]("id")
          .description("ID of the person to delete")
          .example(1)
      )
      .out(jsonBody[User])
      .description("Delete a person")

  val listEndpoint: Endpoint[Unit, Unit, Throwable, List[User], Any] =
    baseEndpoint
      .tag("persons")
      .name("list-persons")
      .get
      .in("persons")
      .out(jsonBody[List[User]])
      .description("List all persons")
