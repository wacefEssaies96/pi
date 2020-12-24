<?php

namespace App\Http\Controllers\Api;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Config;

class Api extends Controller{

    public function auth($user,$pass){
        $odoo = new \Edujugon\Laradoo\Odoo();
        $odoo = $odoo
        ->username($user)
        ->password($pass)
        ->db('shopping1')
        ->host('http://localhost:8069');
        try {
            $odoo->connect();
        } catch (\Throwable $th) {
            return response()->json(['status' => 0]);
        }
        return response()->json(['status' => 1]);
    }

    public function login(Request $request){
        return $this->auth($request->username,$request->password);
    }

    public function viewSaleOrder(Request $request){

        if(gettype($request->username) == 'string'){
            $data = [
                "username"=>$request->username,
                "password"=>$request->password
            ];
        }else{
            foreach($request->all() as $mydata){
                $data = $mydata;
            }
        }

        $odoo = new \Edujugon\Laradoo\Odoo();
        $odoo = $odoo
            ->username($data["username"])
            ->password($data["password"])
            ->db('shopping1')
            ->host('http://localhost:8069');
        try {
            $odoo->connect();
            $list = $odoo->fields('name','image','list_price')->limit(7)->get('product.template');

        } catch (\Throwable $th) {
            return response()->json('invalid credentials :');
        }
        return response()->json($list);
    }

    public function register(){
        $odoo = new \Edujugon\Laradoo\Odoo();
        $odoo = $odoo
            ->username("wacef.stratrait@gmail.com")
            ->password("admin")
            ->db('shopping1')
            ->host('http://localhost:8069');
        $odoo->connect();
        $odoo->create('res.users',[
            'name' => 'test',
            'login' => 'test',
            'new_password' => 'test',
            'company_ids' => [1],
            'company_id' => 1,
        ]);
        //'create', [{'name':"userAPI", 'login':'userapi@gmail.com','company_ids':[1], 'company_id':1, 'new_password':'1234567890'}])
        //$data = $odoo->get('res.users');
        return response("test");
    }
}
