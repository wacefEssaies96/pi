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

    public function viewProducts(Request $request){
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

    public function viewAllUsers(Request $request){
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
            $users = $odoo->where('customer', true)->fields('name','email','phone','image')->get('res.partner');

        } catch (\Throwable $th) {
            return response()->json('invalid credentials :');
        }
        return response()->json($users);
    }

    public function searchProductByName(Request $request){
        if(gettype($request->username) == 'string'){
            $data = [
                "username"=>$request->username,
                "password"=>$request->password,
                "filter"=>$request->filter
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
        $odoo->connect();
        $ids = $odoo->call('product.template', 'search',[
            [
                ['name', 'ilike', $data["filter"]]
            ]
        ]);
        $myArray = json_decode(json_encode($ids), true);
        $product = $odoo->where('id', $myArray)->fields('name','image','list_price')->get('product.template');
        return response($product);
    }
    public function createUser(Request $request){
        $odoo = new \Edujugon\Laradoo\Odoo();
        $odoo = $odoo
        ->username($request->username)
        ->password($request->password)
        ->db('shopping1')
        ->host('http://localhost:8069');
        try {
            $odoo->connect();
            $odoo->create('res.partner',[
                'name' => $request->name,
                'email' => $request->email,
                'phone' => $request->phone
            ]);
        } catch (\Throwable $th) {
            return response()->json(['status' => 0]);
        }
        return response()->json(['status' => 1]);
    }
    public function updateUser(Request $request){
        $odoo = new \Edujugon\Laradoo\Odoo();
        $odoo = $odoo
        ->username($request->username)
        ->password($request->password)
        ->db('shopping1')
        ->host('http://localhost:8069');
        try {
            $odoo->connect();
            $odoo->where('name', $request->name)
            ->update('res.partner',['name' => $request->updatedName,'email' => $request->updatedEmail,'phone'=>$request->updatedPhone]);
        } catch (\Throwable $th) {
            return response()->json(['status' => 0]);
        }
        return response()->json(['status' => 1]);
    }
    public function deleteUser(Request $request){
        $odoo = new \Edujugon\Laradoo\Odoo();
        $odoo = $odoo
        ->username($request->username)
        ->password($request->password)
        ->db('shopping1')
        ->host('http://localhost:8069');
        try {
            $odoo->connect();
            $odoo->where('name', $request->name)
            ->delete('res.partner');
        } catch (\Throwable $th) {
            return response()->json(['status' => 0]);
        }
        return response()->json(['status' => 1]);
    }

}
