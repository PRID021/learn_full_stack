package com.example.demo.controller;

//org.springframework
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

//web.bind.annotation
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.data.responses.Response;
import io.swagger.v3.oas.annotations.Operation;

import java.sql.Types;
//java.util
import java.util.List;
import java.util.Map;

//com.example.demo.data

import com.example.demo.data.domain.UserAccount;
import com.example.demo.data.domain.UserMapper;
import com.example.demo.data.requests.CreateAccountRequest;
import com.example.demo.data.responses.AccountResponse;
import com.example.demo.data.utils.JsonAssets;
import com.example.demo.data.utils.ReadJsonFileToJsonObject;

@RestController
@RequestMapping("/account")
public class AccountController implements CommandLineRunner {

	ReadJsonFileToJsonObject readJsonFileToJsonObject;

	@Autowired
	public JdbcTemplate jdbcTemplate;

	@GetMapping("/getAllAccount")
	@Operation(summary = "Get all account", description = "Get all account", tags = { "Account" })
	public ResponseEntity<?> getAllAccount() {
		SimpleJdbcCall getAllUserAccount = new SimpleJdbcCall(jdbcTemplate).withProcedureName("GET_ALL_USER_ACCOUNT")
			.returningResultSet("out", new UserMapper());
		Map<String, Object> out = getAllUserAccount.execute();
		Object outObject = out.get("out");
		List<UserAccount> customers = null;
		if (outObject instanceof List) {
			customers = (List<UserAccount>) out.get("out");
                        customers.forEach( (customer) -> {
                            System.out.println(customer.toString());
                        });
			return new ResponseEntity<>(new AccountResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
					"Sign in successful", customers

			), HttpStatus.OK);

		}
                return new ResponseEntity<>(new AccountResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                "Sign in failed", null), HttpStatus.BAD_REQUEST);
                

	}

	@PostMapping("/createNewUserAccount")
	public ResponseEntity<?> createNewUserAccount(

			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Create new user account description",
					required = true,
					content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
							examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
									name = "Create new user account name",
									value = JsonAssets.CREATE_NEW_USER_ACCOUNT_REQUEST_ACCOUNT,
									summary = "Create new user account summary",
									externalValue = "Create new user account externalValue"),
							schema = @io.swagger.v3.oas.annotations.media.Schema(
									implementation = CreateAccountRequest.class))) @RequestBody

			CreateAccountRequest postRequest) {
		System.out.println(postRequest.toString());
		try {
			SimpleJdbcCall getAllUserAccount = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("CREATE_NEW_USER_ACCOUNT")
				.declareParameters(new SqlParameter("USER_NAME", Types.NVARCHAR),
						new SqlParameter("CREATE_TIME", Types.NVARCHAR), new SqlParameter("ACTIVE_STATUS", Types.BIT),
						new SqlOutParameter("OUT", Types.INTEGER));
			Map<String, Object> result = getAllUserAccount.execute(postRequest.toMap());
			System.out.println(result);
			int numRowsAffected = (Integer) result.get("OUT");
			if (numRowsAffected == 1) {
				return new ResponseEntity<>(new Response(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
						"Create new user account successful"), HttpStatus.OK);
			}
			return new ResponseEntity<>(
					new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
							HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "Create new user account failed"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (Exception e) {
			return new ResponseEntity<>(
					new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(),
							HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.toString()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void run(String... args) throws Exception {
		readJsonFileToJsonObject = new ReadJsonFileToJsonObject();
	}

}
