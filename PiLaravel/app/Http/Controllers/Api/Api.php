<?php

namespace App\Http\Controllers\Api;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Config;

class Api extends Controller{
    public function a($user,$pass){
        $odoo = new \Edujugon\Laradoo\Odoo();
        $odoo = $odoo
        ->username($user)
        ->password($pass)
        ->db('shopping1')
        ->host('http://localhost:8069');
        Config::write('global.user',$user);
        Config::write('global.pass',$pass);
        try {
            $odoo->connect();
        } catch (\Throwable $th) {
            return response()->json(['status' => 0]);
        }
        return response()->json(['status' => 1]);
    }

    public function login(Request $request){
        if(empty($request->username) || empty($request->password) ){
            $configUsername = Config::get('global.user');
            $configPassword = Config::get('global.pass');
            return $this->a($configUsername,$configPassword);
        }
        else{
          return $this->a($request->username,$request->password);
        }
    }

    public function viewSaleOrder(Request $request){

        if(gettype($request->username) == 'string'){
            $test = [
                "username"=>$request->username,
                "password"=>$request->password
            ];
        }else{
            foreach($request->all() as $mydata){
                $test = $mydata;
            }
        }

        $odoo = new \Edujugon\Laradoo\Odoo();
        $odoo = $odoo
            ->username($test["username"])
            ->password($test["password"])
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
}
