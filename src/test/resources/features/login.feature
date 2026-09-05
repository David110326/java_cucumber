Feature: 登录功能测试
  描述：测试SauceDemo网站的登录功能

  Background:
    Given open login page

  @smokes @login
  Scenario: 使用正确的用户名和密码登录成功
    When input user_name "standard_user" password "secret_sauce"
    And user click login
    Then usr should login success, title display "Products"

  @negative @login
  Scenario Outline: 使用错误的凭据登录失败
    When input user_name "<username>" password "<password>"
    And user click login
    Then user should see the error message "<errorMessage>"

    Examples:
      | username       | password      | errorMessage                                    |
      | wrong_user     | secret_sauce  | Username and password do not match any user...  |
      | standard_user  | wrong_pass    | Username and password do not match any user...  |
      |                | secret_sauce  | Username is required                            |
      | standard_user  |               | Password is required                            |

  @smoke
  Scenario: 登录成功后可以看到商品列表
    When input user_name "standard_user" password "secret_sauce"
    And user click login
    Then page at least have six product