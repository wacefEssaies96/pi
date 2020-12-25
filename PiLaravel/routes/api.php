<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});


Route::post('/login','Api\Api@login');
//Show Products
Route::post('/list','Api\Api@viewProducts');
//View Users
Route::post('/users','Api\Api@viewAllUsers');
//Search products by name
Route::post('/product','Api\Api@searchProductByName');

//Create,Update,Delete user
Route::post('/createUser','Api\Api@createUser');
Route::post('/updateUser','Api\Api@updateUser');
Route::post('/deleteUser','Api\Api@deleteUser');
