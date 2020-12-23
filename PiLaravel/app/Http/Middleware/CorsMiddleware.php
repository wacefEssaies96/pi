<?php

namespace App\Http\Middleware;

use Closure;

class CorsMiddleware
{
    
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */
    public function handle($request, Closure $next)
    {
        

        header("Access-Control-Allow-Origin : *");

        $headers = [
            'Access-Control-Allow-Methods' => ' POST, GET, OPTIONS, PUT, DELETE',
            'Access-Control-Allow-Headers', 'Origin, Content-Type, X-Auth-Token, Authorization ',
        ];
        if($request->getMethod() == 'OPTIONS' ){
            return response()->json('ok',200,$headers);
        }
        $response = $next($request);
        foreach ($headers as $key => $value){
            $response->header($key, $value);
        }
        return $response;
    }
}
